/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/javascript.js to edit this template
 */
function procederAlPago() {
    // Get form elements
    const form = document.getElementById('payment-form');
    const metodoPago = document.getElementById('metodoPago').value;
    const correoCliente = document.getElementById('correoCliente').value;

    // Validate required fields
    if (!form.checkValidity()) {
        form.classList.add('was-validated');
        return;
    }

    // Get cart items
    const items = [...document.querySelectorAll('[data-id]')].map(item => ({
            id: parseInt(item.getAttribute('data-id')),
            nombre: item.closest('tr').querySelector('h6').textContent,
            cantidad: parseInt(item.parentElement.querySelector('.cantidad-producto').textContent),
            precio: parseFloat(item.closest('tr').querySelector('td:nth-child(2)').textContent.replace('₡', '').replace(',', '')),
            subtotal: parseFloat(item.closest('tr').querySelector('td:nth-child(4)').textContent.replace('₡', '').replace(',', ''))
        }));

    // Get total amount
    const total = parseFloat(document.querySelector('.card-body div span:last-child').textContent
            .replace('₡', '')
            .replace(',', ''));

    // Create payment data object
    const datosPago = {
        metodoPago: metodoPago
    };

    // Add card details if payment method is card
    if (metodoPago === 'TARJETA') {
        datosPago.numeroTarjeta = document.getElementById('numeroTarjeta').value;
        datosPago.nombreTarjeta = document.getElementById('nombreTarjeta').value;
        datosPago.fechaExpiracion = document.getElementById('fechaExpiracion').value;
        datosPago.cvv = document.getElementById('cvv').value;
    }

    // Create complete payment data
    const pagoData = {
        items: items,
        total: total,
        datosPago: datosPago,
        correoCliente: correoCliente
    };

    // Send payment request
    fetch('/pago/procesar', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'X-CSRF-TOKEN': document.querySelector('meta[name="_csrf"]').getAttribute('content')
        },
        body: JSON.stringify(pagoData)
    })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Error en la respuesta del servidor');
                }
                return response.json();
            })
            .then(result => {
                if (result.success) {
                    window.location.href = result.redirectUrl;
                } else {
                    throw new Error(result.message || 'Error al procesar el pago');
                }
            })
            .catch(error => {
                console.error('Error:', error);
                alert('Error al procesar el pago: ' + error.message);
            });
}

// Format credit card number with spaces
document.getElementById('numeroTarjeta')?.addEventListener('input', function (e) {
    let value = e.target.value.replace(/\s+/g, '').replace(/[^0-9]/gi, '');
    let formattedValue = value.match(/.{1,4}/g)?.join(' ') || value;
    e.target.value = formattedValue.substring(0, 19); // 16 digits + 3 spaces
});

// Format expiration date
document.getElementById('fechaExpiracion')?.addEventListener('input', function (e) {
    let value = e.target.value.replace(/\D/g, '');
    if (value.length >= 2) {
        value = value.substring(0, 2) + '/' + value.substring(2);
    }
    e.target.value = value.substring(0, 5);
});

// Format CVV
document.getElementById('cvv')?.addEventListener('input', function (e) {
    e.target.value = e.target.value.replace(/\D/g, '').substring(0, 4);
});