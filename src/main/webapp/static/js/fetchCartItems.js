export function postFetch(data){
    fetch("/cart?quantity=" + data.quantity + "&id=" +  data.id).then()
}