import {clearInnerHTML, fillHtmlElementWithProducts} from "./util.js";

const filterLinks = document.getElementsByClassName("category-link");
for (const link of filterLinks){
    link.addEventListener('click', fetchByFilter)
}

function fetchByFilter(event){
    const filter = event.currentTarget.dataset.filter;
    const filterID = event.currentTarget.dataset.id;
    fetch("/filter-products?filter=" + filter + "&id=" +  filterID)
        .then(response  => response.json())
        .then(data => displayResult(data));
}

function displayResult(data){
    const divToFill = document.getElementById('products');
    clearInnerHTML(divToFill);
    fillHtmlElementWithProducts(divToFill, data);

}