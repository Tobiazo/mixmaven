import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "./pages/Layout";
// import "./styles/App.css";
import Home from "./pages/Home";
import AddDrink from "./pages/AddDrink";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="add" element={<AddDrink />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
