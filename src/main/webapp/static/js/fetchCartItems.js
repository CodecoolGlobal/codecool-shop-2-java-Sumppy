import {deleteModals} from "./util.js";

export function postFetch(data, event){
    event.preventDefault();
    deleteModals();
    fetch("/cart?quantity=" + data.quantity + "&id=" +  data.id).then()
}