export function increaseAmount(id, event){
    event.preventDefault();
    const currentAmount = document.querySelector(".quantity-amount");
    const currentAmountValue = parseInt(currentAmount.innerHTML);
    const price = parseInt(document.getElementById(id).dataset.price);

    currentAmount.innerHTML = (currentAmountValue + 1).toString();
    changeTotalPrice(currentAmountValue + 1, price);
}

export function decreaseAmount(id, event){
    event.preventDefault();
    const currentAmount = document.querySelector(".quantity-amount");
    const currentAmountValue = parseInt(currentAmount.innerHTML);
    const price = parseInt(document.getElementById(id).dataset.price);

    if (currentAmountValue > 1){
        currentAmount.innerHTML = (currentAmountValue - 1).toString();
        changeTotalPrice(currentAmountValue-1, price)
    }
}

function changeTotalPrice(amount, priceOfOne){
    const totalPrice = document.querySelector(".price-amount");
    totalPrice.innerHTML = (amount * priceOfOne).toString() + " Cr";
}