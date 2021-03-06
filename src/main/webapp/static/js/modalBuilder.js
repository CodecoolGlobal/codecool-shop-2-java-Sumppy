export function buildModal(id){
    let modalDiv = document.getElementById(id);
    const price = modalDiv.dataset.price;
    console.log(price)
    modalDiv.innerHTML = `<div class="modal-content">
        <div class="modal-header">
            <span class="close">&times</span>
        </div>
        <div class="modal-body">
            <div class="quantity-container">
                <div class="amount">    
                    <p>Amount: </p>
                </div>        
                <div class="product-quantity">
                    <a href="#" class="decrease-quantity">-</a>
                    <p class="quantity-amount">1</p>
                    <a href="#" class="increase-quantity">+</a>
                </div>
            </div>
            <div class="price-container">
                <p class="price-amount">${price} Cr</p>
            </div>
            <div class="button-container">
                <a id="buy" class="btn" href="#">Add to cart</a>
            </div>
        </div>
    </div>`
}