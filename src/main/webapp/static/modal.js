const modal = document.getElementById('modal');
const addToCartLink = document.getElementById('add-to-cart');
const X = document.getElementsByClassName('close')[0];

addToCartLink.addEventListener('click', handleClickOpen)
X.addEventListener('click', handleClickClose)

function handleClickOpen(){
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