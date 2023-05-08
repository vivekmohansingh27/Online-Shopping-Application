const navbar = () => {
  return `<nav class="navbar navbar-expand-lg bg-primary fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">ShopKaro</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNavDropdown"
        aria-controls="navbarNavDropdown"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse " id="navbarNavDropdown">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" id="home" href="#">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="product" href="#">Products</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="cart" href="#">cart</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" id="order" href="#">order</a>
          </li>
          
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              role="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              Dropdown link
            </a>
            <ul class="dropdown-menu">
              <li><a class="dropdown-item" href="#">Action</a></li>
              <li><a class="dropdown-item" href="#">Another action</a></li>
              <li>
                <a class="dropdown-item" href="#">Something else here</a>
              </li>
              
            </ul>
          </li>
          <li class="nav-item">
            <button type="button" id="logout-btn" class="btn button-33">Log Out</button>
          </li>
        </ul>
      </div>
    </div>
  </nav>
    `;
};

export default navbar;
