import data from "../../fixtures/data.json";
import "../styles/Home.css";

const Home = () => {
  return (
    <>
      <h1>DRINKS</h1>
      <ul className="drinkBox">
        {data.map((drink) => {
          return (
            <li key={drink.id} className="drinkItem">
              <h2>{drink.name}</h2>
              <p>{drink.alcoholContent}%</p>
              <ol>
                {drink.ingredients.map((ingredient) => (
                  <li className="ingredientItem">{ingredient.name}</li>
                ))}
              </ol>
            </li>
          );
        })}
      </ul>
    </>
  );
};

export default Home;
