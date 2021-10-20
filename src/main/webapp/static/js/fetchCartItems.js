export function postFetch(data){
    fetch('/cart', {
        method: 'POST',
        headers: {'Content-Type': 'application/json',},
        body: JSON.stringify(data),
    }).then(r => r.json())
}