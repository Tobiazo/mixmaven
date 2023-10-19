import data from "../../fixtures/data.json";
import "../styles/Home.css";
import LocalBarIcon from '@mui/icons-material/LocalBar';
import DeleteOutlineIcon from '@mui/icons-material/DeleteOutline';
import EditIcon from '@mui/icons-material/Edit';

const Home = () => {
  return (
    <>
      <h1>DRINKS</h1>
      <ul className="drinkBox">
        {data.map((drink) => {
          return (
            <li key={drink.id} className="drinkItem">
              <h2> <LocalBarIcon /> {drink.name}</h2>
              <ol>
                {drink.ingredients.map((ingredient) => (
                  <li className="ingredientItem">{ingredient.name}</li>
                ))}
              </ol>
              <p>
              <DeleteOutlineIcon />
              <EditIcon />
              </p>
            </li>
          );
        })}
      </ul>
    </>
  );
};

export default Home;
