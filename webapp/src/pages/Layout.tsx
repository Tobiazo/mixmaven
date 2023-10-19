import { Outlet, Link } from 'react-router-dom'
import { FavoriteBorderOutlined } from '@mui/icons-material'
import '../styles/Layout.css'

const Layout = () => {
  return (
    <div className="layout-container">
      <nav>
        <h1>MiXMaven</h1>
        <ul>
          <li>
            <Link to="/">Home</Link>
          </li>
          <li>
            <Link to="/new">New drink</Link>
          </li>
        </ul>
      </nav>

      <div className="layout-content">
        <Outlet />
      </div>

      <div className="layout-footer">
        Made with <FavoriteBorderOutlined /> by GR2331
      </div>
    </div>
  )
}

export default Layout
