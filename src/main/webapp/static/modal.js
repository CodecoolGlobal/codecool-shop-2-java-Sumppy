const modal = document.getElementById('modal');
const addToCartLink = document.querySelectorAll('#add-to-cart');
const X = document.getElementsByClassName('close')[0];

for (const link of addToCartLink) {
    link.addEventListener('click', handleClickOpen)
}

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