import {addEventListenerToAddToCartButtons} from "./util.js";
import {buildModal} from "./modalBuilder.js";

let X;
let modal;

addEventListenerToAddToCartButtons();


export function handleClickOpen(event){
    event.preventDefault()
    let id = event.target.dataset.productid.toString()
    buildModal(id);
    X = document.getElementsByClassName('close')[0];
    modal = document.getElementById(id)
    modal.style.display = "block";
    X.addEventListener('click', handleClickClose)
}

function handleClickClose(){
    modal.style.display = "none";
}

window.onclick = function (e){
    if(e.target === modal){
       handleClickClose()
    }
}