document.addEventListener("DOMContentLoaded", () => {
  const input = document.getElementById("buscador");
  const resultados = document.getElementById("resultados-busqueda");

  let timeout = null;

  input.addEventListener("input", () => {
    const filtro = input.value.trim();
    clearTimeout(timeout);

    if (filtro.length < 2) {
      resultados.classList.add("d-none");
      resultados.innerHTML = "";
      return;
    }

    // Esperar un poco antes de buscar (para no hacer fetch en cada tecla)
    timeout = setTimeout(() => {
      fetch(`/api/productos?buscar=${encodeURIComponent(filtro)}`)
        .then((resp) => resp.json())
        .then((productos) => {
          if (productos.length === 0) {
            resultados.innerHTML = `<div class="p-2 text-muted">No se encontraron productos</div>`;
          } else {
            console.log("api_productos", productos);
            // Mapeamos los productos
            let html = productos
              .map(
                (p) => `
<a href="/producto/${p.id}" class="text-decoration-none text-reset">
  <div class="card mb-3 shadow-sm p-2">
    <div class="row g-0 align-items-center">
      <div class="col-md-2 text-center">
        <img src="${p.imagen}" class="img-fluid rounded-start p-2" alt="Imagen producto"
          style="max-height: 60px; object-fit: contain;">
      </div>
      <div class="col-md-5">
        <p class="text-muted mb-1 small text-uppercase">${p.presentacion}</p>
        <h6 class="card-title mb-1 fw-semibold">${p.nombre}</h6>
        <p class="mb-1 text-secondary small">${p.marca}</p>
      </div>
      <div class="col-md-2">
        <div class="d-flex align-items-center justify-content-center mt-2">
          <span class="fw-bold fs-6">S/ ${p.precio}</span>
        </div>
      </div>
      <div class="col-md-3">
        <div class="d-flex align-items-center justify-content-center mt-2">
          <button class="btn btn-main btn-sm px-3 btn-add-carrito"
                  data-id="${p.id}">
            Agregar al carrito
          </button>
        </div>
      </div>
    </div>
  </div>
</a>
`
              )
              .join("");
            // Enlace para ver todos los resultados
            html += `
          <div 
            class="p-2 text-center bg-light text-primary fw-semibold"
            style="cursor: pointer;"
            onclick="window.location.href='/busqueda?query=${encodeURIComponent(
              filtro
            )}'"
          >
            Ver todos los resultados →
          </div>
        `;

            resultados.innerHTML = html;
          }
          resultados.classList.remove("d-none");
        })
        .catch((err) => console.error("Error cargando productos:", err));
    }, 300);
  });

  // Ocultar cuando se hace clic fuera
  document.addEventListener("click", (e) => {
    if (!resultados.contains(e.target) && e.target !== input) {
      resultados.classList.add("d-none");
    }
  });
});

// Si presiona Enter → ir a la página completa de resultados
function enviarBusqueda(event) {
  const filtro = document.getElementById("buscador").value.trim();
  if (!filtro) {
    event.preventDefault();
    return false;
  }
  // Deja que el formulario se envíe normalmente a /buscar?query=...
  return true;
}

//Para que funcione el agregar al carrito desde resultados de búsqueda
//// Función para actualizar el número del carrito
function actualizarCarritoBadge() {
  fetch("/carrito/cantidad")
    .then((resp) => resp.text())
    .then((cantidad) => {
      const badge = document.querySelector(".cart-badge");
      if (badge) badge.textContent = cantidad;
    })
    .catch((err) => console.error("Error actualizando badge:", err));
}

// Delegación de eventos para botones "Agregar al carrito"
document.addEventListener("click", (e) => {
  const btn = e.target.closest(".btn-add-carrito");
  if (!btn) return;

  e.preventDefault();

  const id = btn.dataset.id;
  if (!id) return console.error("No se encontró data-id en el botón");

  fetch("/carrito/agregar", {
    method: "POST",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: `productoId=${encodeURIComponent(id)}`,
  })
    .then((resp) => {
      if (!resp.ok) throw new Error("Error en respuesta del servidor");
      console.log(`Producto ${id} agregado al carrito`);
      actualizarCarritoBadge();
      location.reload();
    })
    .catch((err) => console.error("Error agregando producto:", err));
});
