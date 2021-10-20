import {addEventListenerToAddToCartButtons} from "./util.js";

const modal = document.getElementById('modal');
const X = document.getElementsByClassName('close')[0];


addEventListenerToAddToCartButtons();

X.addEventListener('click', handleClickClose)

export function handleClickOpen(event){
    event.preventDefault()
    modal.style.display = "block";
}

function handleClickClose(){
    modal.style.display = "none";
}

window.onclick = function (e){
    if(e.target === modal){
       handleClickClose()
    }
}