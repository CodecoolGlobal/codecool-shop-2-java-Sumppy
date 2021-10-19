import {handleClickOpen} from "./modal.js";

export function clearInnerHTML(htmlElement){
    htmlElement.innerHTML = "";
}

export function fillHtmlElementWithProducts(htmlElement, data){
    htmlElement.innerHTML = data.map(d => {
        return `
        <div class="col col-sm-12 col-md-6 col-lg-4">
            <div class="card">
                <img class="" src='/static/img/product_${d.id}.jpg' alt="" />
                <div class="card-header">
                    <h4 class="card-title">${d.name}</h4>
                    <p class="card-text">${d.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${d.defaultPrice} ${d.defaultCurrency}</p>
                    </div>
                    <div class="card-text">
                        <a id="add-to-cart" class="btn btn-success" href="#">Add to cart</a>
                    </div>
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