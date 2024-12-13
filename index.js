console.log("Backend");

document.getElementById("divTeste").innerText = "Frontend";

var numero1 = 2;
var numero2 = 3;

console.log(numero1 + numero2);

// numero1 = "2";
console.log(numero2 + numero1);

document.getElementById("divTeste").innerText = numero2 + numero1;

function somar() {
    document.getElementById("divTeste").innerText = parseInt(document.getElementById("txtNumero1").value) + parseInt(document.getElementById("txtNumero2").value);
}
