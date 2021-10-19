import {clearInnerHTML, fillHtmlElementWithProducts} from "./util.js";

function fetchByFilter(filter, filterID){
    fetch("/filter-products?filter=" + filter + "&id=" +  filterID)
        .then(response  => response.json())
        .then(data => displayResult(data));
}

function displayResult(data){
    const divToFill = document.getElementById('products');
    clearInnerHTML(divToFill);
    fillHtmlElementWithProducts(divToFill, data);

}