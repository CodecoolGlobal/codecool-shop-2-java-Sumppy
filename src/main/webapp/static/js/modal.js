import {addEventListenerToAddToCartButtons, deleteModals, addEventListenerToAmountLinks, addEventListenerToAddToCart} from "./util.js";
import {buildModal} from "./modalBuilder.js";

let X;
let modal;

addEventListenerToAddToCartButtons();


export function handleClickOpen(event){
    event.preventDefault();
    deleteModals();
    let id = event.target.dataset.productid.toString()
    buildModal(id);
    X = document.getElementsByClassName('close')[0];
    modal = document.getElementById(id)
    modal.style.display = "block";
    X.addEventListener('click', handleClickClose);
    addEventListenerToAmountLinks(id);
    addEventListenerToAddToCart(id);
}

function handleClickClose(){
    modal.style.display = "none";
}

window.onclick = function (e){
    if(e.target === modal){
       handleClickClose()
    }
}