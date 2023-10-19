import { Outlet, Link } from "react-router-dom";
import '../styles/Layout.css'

const Layout = () => {
  return (
    <div className="layout-container">
      <nav>
        <h1>MIXMAVEN</h1>
        <ul>
          <li><Link to="/">Home</Link></li>
          <li><Link to="/add">AddDrinks</Link></li>
        </ul>
      </nav>

      <div className="layout-content">
        <Outlet />
      </div>

      <div className="layout-footer">
        Made with {"<"}3 by GR2331
      </div>
    </div>
  );
};

export default Layout;
