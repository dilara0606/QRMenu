function injectStyles() {
    const style = `
    <style>
        .drag-handle {
            cursor: move;
            text-align: center;
        }
        .dragging {
            opacity: 0.5;
        }
        .over {
            border: 2px dashed #000;
        }
    </style>
    `;
    document.head.insertAdjacentHTML('beforeend', style);
  }
  
  function fetchData() {
  fetch("http://localhost:8088/api/v1/admin/item/all-item", {
    method: "GET",
  })
  .then((response) => response.json())
  .then((data) => {
  
    const tableBody = document.querySelector("tbody"); // Select the table body
    tableBody.innerHTML = ""; // Clear the table
    
    // Function to create table row
    function createRow(item) {
      const row = document.createElement("tr");
      row.dataset.id = item.id; // Store item ID in row for later use
  
      // Add drag handle cell
      const dragHandleCell = document.createElement("td");
      dragHandleCell.className = "drag-handle";
      dragHandleCell.innerHTML = `<i class="fas fa-grip-vertical"></i>`;
      row.appendChild(dragHandleCell);
  
      // Add image cell
      const imgCell = document.createElement("td");
      imgCell.innerHTML = `
        <div class="d-flex px-2 py-1">
          <div>
            <img src="${item.imageUrl}" class="avatar avatar-lg me-3 border-radius-lg" alt="${item.name}">
          </div>
          <div class="d-flex flex-column justify-content-center">
            <h6 class="mb-0 text-sm">${item.name}</h6>
          </div>
        </div>
      `;
      row.appendChild(imgCell);
  
      // Add Prices cell
      const PriceCell = document.createElement("td");
      PriceCell.className = "align-middle text-center text-sm";
      PriceCell.innerHTML = `
         <span class="badge badge-sm bg-gradient-secondary">${item.price} ₺</span>
      `;
      
      row.appendChild(PriceCell);
  
      // Add date cell
      const dateCell = document.createElement("td");
      dateCell.className = "align-middle text-center";
      dateCell.innerHTML = `
        <span class="text-secondary text-xs font-weight-bold">${item.updatedAt}</span>
      `;
      row.appendChild(dateCell);
  
      // Add edit cell
      const actionCellEdit = document.createElement("td");
      actionCellEdit.className = "align-middle";
      actionCellEdit.innerHTML = `
        <a href="javascript:;" class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Edit" data-id="${item.id}">
          Edit
        </a>
      `;
      row.appendChild(actionCellEdit);
  
      // Add delete cell
      const actionCellDelete = document.createElement("td");
      actionCellDelete.className = "align-middle";
      actionCellDelete.innerHTML = `
        <a href="javascript:;" class="text-secondary font-weight-bold text-xs" data-toggle="tooltip" data-original-title="Delete" data-id="${item.id}">
          Delete
        </a>
      `;
      row.appendChild(actionCellDelete);
  
      return row;
    }
    
    data.forEach(item => {
      tableBody.appendChild(createRow(item));
    });
  
 
    // Add click event listeners for edit links
    document.querySelectorAll('a[data-original-title="Edit"]').forEach(link => {
      link.addEventListener('click', (event) => {
        document.getElementById('editRow').style.display = 'block';
        const id = event.target.getAttribute('data-id');
        const item = data.find(d => d.id == id); // Find the item with the matching ID
        if (item) {
          // Fill in the form with the item data
          document.getElementById('nameInput').value = item.name;
          document.getElementById('priceInput').value = item.price;
          document.getElementById('descriptionInput').value = item.description;
          document.getElementById('imageTitle').textContent = item.imageUrl ? "Image Selected" : "No Image Selected";
          document.getElementById('selectedImage').src = item.imageUrl || "";
          document.getElementById('selectedImage').style.display = item.imageUrl ? 'block' : 'none';
          document.getElementById('imageUpload').dataset.itemId = item.id; // Store the item ID for saving later
  
          console.log(item.imageUrl);
        }
      });
    });
  
    // Add click event listeners for delete links
    document.querySelectorAll('a[data-original-title="Delete"]').forEach(link => {
      link.addEventListener('click', (event) => {
        const id = event.target.getAttribute('data-id');
        if (confirm('Are you sure you want to delete this product?')) {
          deleteProduct(id);
        }
      });
    });
  
  })
  .catch((error) => {
    console.error("Error fetching data:", error);
  });
  }
  
  // Save button event listener
  document.querySelector('button[type="submit"]').addEventListener('click', () => {
    const id = document.getElementById('imageUpload').dataset.itemId;
    const name = document.getElementById('nameInput').value;
    const description = document.getElementById('descriptionInput').value;
    const imageUrl = document.getElementById('selectedImage').src;
    const price = document.getElementById('priceInput').value;
  
    if (id) {
      fetch(`http://localhost:8088/api/v1/admin/item/edit-item/${id}`, {
        method: "PUT",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name: name,
          description: description,
          price: price,
          imageUrl: imageUrl
        })
      })
      .then(response => response.json())
      .then(data => {
        fetchData();
        document.getElementById('editRow').style.display = 'none';
        showToast("Product Update Successful!") // Refresh the table with updated data
      })
      .catch(error => {
        console.error("Error updating product:", error);
      });
    }
  });  
  
  function deleteProduct(id) {
    fetch(`http://localhost:8088/api/v1/admin/item/delete-item/${id}`, {
        method: "DELETE",
    })
    .then((response) => response.text()) // Get response as text
    .then((text) => {
        if (text.trim() === "Category deleted successfully") { // Check the response
            showToast('Product deleted successfully!');
            fetchData(); 
        } else {
            showToast('An error occurred while deleting the product.');
        }
    })
    .catch((error) => {
        console.error("Error deleting product:", error);
        showToast('An error occurred while deleting the product.');
    });
  }
  
  function showToast(message) {
    const toastEl = document.getElementById('successToast');
    const toastBody = toastEl.querySelector('.toast-body');
    const toastHeaderSpan = toastEl.querySelector('.toast-header .me-auto');
    const toastHeaderSmall = toastEl.querySelector('.toast-header small');
  
    toastBody.textContent = message;
    toastHeaderSpan.textContent = 'Notification'; // Customize as needed
    toastHeaderSmall.textContent = 'Just now'; // Customize as needed
  
    // Show the toast
    const toast = new bootstrap.Toast(toastEl, {
        delay: 4000 // 3 seconds
    });
    toast.show();
  }
  
  function createProduct() {
    const name = document.getElementById('nameInput2').value;
    const description = document.getElementById('descriptionInput2').value;
    const imageUrl = document.getElementById('selectedImage2').src;
    const price = document.getElementById('priceInput2').value;
    
    if (!name || !description) {
      alert('Please fill out all fields.');
      return;
    }
    
    const product = {
      name: name,
      description: description,
      price: price,
      imageUrl: imageUrl
    };
    
    fetch('http://localhost:8088/api/v1/admin/item/create-item', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(product)
    })
    .then(response => {
      if (!response.ok) {
        throw new Error('Network response was not ok');
      }
      return response.json();
    })
    .then(data => {
      console.log(data);
      showToast("Product created successfully!");
      loadCategories(data.id);
      fetchData();
    })
    .catch(error => {
      console.error('There was a problem with the fetch operation:', error);
      alert('Failed to create product.');
    });
  }
  
  document.addEventListener("DOMContentLoaded", () => {
    document.getElementById("create-product").addEventListener("click", createProduct);
  });
  
  function toggleCreateRow() {
    var editRowProduct = document.getElementById('editRowProduct');
    editRowProduct.style.display = editRowProduct.style.display === 'none' ? 'block' : 'none';
  }
  
  function previewImage2(event) {
    var reader = new FileReader();
    reader.onload = function() {
      var output = document.getElementById('selectedImage2');
      output.src = reader.result;
      output.style.display = 'block';
    }
    reader.readAsDataURL(event.target.files[0]);
    document.getElementById('imageTitle2').innerText = event.target.files[0].name;
  }
  
  function loadCategories(productId) {
    fetch('http://localhost:8088/api/v1/admin/category/categories')
      .then(response => response.json())
      .then(categories => {
        var categoriesContainer = document.getElementById('categoriesContainer');
        categoriesContainer.innerHTML = '';
        categories.forEach(category => {
          console.log(category);
          var categoryCard = document.createElement('div');
          categoryCard.className = 'card m-2 p-2';
          categoryCard.style.width = '150px';
          categoryCard.style.cursor = 'pointer';
          categoryCard.innerHTML = `
            <div class="d-flex justify-content-between align-items-center">
              <span>${category.name}</span>
              <i class="material-icons" onclick="addCategoryToProduct(${category.id}, ${productId})">add</i>
            </div>
          `;
          categoriesContainer.appendChild(categoryCard);
        });
        document.getElementById('categoriesRow').style.display = 'flex';
      })
      .catch(error => console.error('Error fetching categories:', error));
  }
  
  function addCategoryToProduct(categoryId, productId) {
    fetch(`http://localhost:8088/api/v1/admin/add-item/${categoryId}?itemId=${productId}`)
      .then(response => {
        if (response.ok) {
          console.log(`Category ${categoryId} added to product`);
          showToast("Category added to product")
        } else {
          console.error(`Error adding category ${categoryId} to product`);
        }
      })
      .catch(error => console.error('Error:', error));
  }

  fetchData();