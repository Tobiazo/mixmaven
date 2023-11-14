import { BrowserRouter, Routes, Route } from 'react-router-dom'
import Layout from './pages/Layout'
// import "./styles/App.css";
import Home from './pages/Home'
import NewDrink from './pages/NewDrink'
import EditDrink from './pages/EditDrink'
import { useQuery } from '@tanstack/react-query'
import { getDrinks } from './api/drinks'

function App() {
  const getDrinksQuery = useQuery({
    queryKey: ['drinks'],
    queryFn: getDrinks,
  })

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home query={getDrinksQuery} />} />
          <Route path="new" element={<NewDrink />} />
          <Route
            path="edit/:id"
            element={<EditDrink query={getDrinksQuery} />}
          />
        </Route>
        <Route path="*" element={<p>404 Not found</p>} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
