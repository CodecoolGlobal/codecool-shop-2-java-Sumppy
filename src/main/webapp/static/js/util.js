import {handleClickOpen} from "./modal.js";
import {closeSidebar, openSidebar} from "./sidebar.js";
import {increaseAmount, decreaseAmount} from "./modalAmount.js";
import {postFetch} from "./fetchCartItems.js";

export function clearInnerHTML(htmlElement){
    htmlElement.innerHTML = "";
}

export function fillHtmlElementWithProducts(htmlElement, data){
    htmlElement.innerHTML = data.map(d => {
        return `
        <div class="col col-sm-12 col-md-6 col-lg-4" id="product-card">
            <div class="card">
                <img class="" src='/static/img/product_${d.id}.jpg' alt="" height="400px"/>
                <div class="card-header">
                    <h4 class="card-title">${d.name}</h4>
                    <p class="card-text">${d.description}</p>
                </div>
                <div class="card-body">
                    <div class="price-text">
                        <p class="lead">${d.defaultPrice} ${d.defaultCurrency}</p>
                    </div>
                    <div class="buy-button">
                        <a id="add-to-cart" class="btn" href="#" data-productId="${d.id}">Buy</a>
                    </div>
                     <div id="${d.id}" data-price="${d.defaultPrice}" class="modal"></div>
                </div>
            </div>
        </div>
        `
    }).join("");
}

export function addEventListenerToAddToCartButtons(){
    const buttons = document.querySelectorAll('#add-to-cart');
    for(const button of buttons){
        button.addEventListener('click', handleClickOpen)
    }
}

export function deleteModals(){
    for (const modal of document.getElementsByClassName('modal')) {
        modal.style.display='none';
        clearInnerHTML(modal);
    }
}

export function addEventListenerToSidebarElements(){
    document.querySelector('.closebtn').addEventListener('click', closeSidebar);
    document.querySelector('.sidebar-button').addEventListener('click', openSidebar);
}

export function addEventListenerToAmountLinks(id){
    const increase = document.querySelector(".increase-quantity");
    const decrease = document.querySelector(".decrease-quantity");

    increase.addEventListener('click', () => increaseAmount(id, event));
    decrease.addEventListener('click', () => decreaseAmount(id, event));
}

export function addEventListenerToAddToCart(id){
    const button = document.getElementById('buy');
    button.addEventListener('click', ()=>postFetch(collectCartContent(id), event))
}

function collectCartContent(id){
    return {
        'quantity': parseInt(document.querySelector('.quantity-amount').innerHTML),
        'id': parseInt(id)
    }
}