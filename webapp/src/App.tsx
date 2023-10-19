import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Layout from './pages/Layout'
// import "./styles/App.css";
import Home from './pages/Home'
import NewDrink from './pages/NewDrink'

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="new" element={<NewDrink />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default App
