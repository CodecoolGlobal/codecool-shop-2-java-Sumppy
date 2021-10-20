import {handleClickOpen} from "./modal.js";
import {closeSidebar, openSidebar} from "./sidebar.js";

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
                    <div class="card-text">
                        <p class="lead">${d.defaultPrice} ${d.defaultCurrency}</p>
                    </div>
                    <div class="card-text">
                        <a id="add-to-cart" class="btn btn-success" href="#" data-productId="${d.id}">Add to cart</a>
                    </div>
                     <div id="${d.id}" class="modal"></div>
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