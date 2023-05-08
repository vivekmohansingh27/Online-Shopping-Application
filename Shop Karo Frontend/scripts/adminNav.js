function adminNav() {
  return `<nav class="navbar navbar-expand-sm bg-body-tertiary fixed-top">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Admin</a>
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarNav"
        aria-controls="navbarNav"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" id="home"
              >Home</a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" id="products">Products</a>
          </li>
          <li class="nav-item">
            <button type="button" id="logout-btn" class="btn button-33">Log Out</button>
          </li>
        </ul>
      </div>
    </div>
  </nav>`;
}

export default adminNav;
