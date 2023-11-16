# MixMaven Webapp

This is an alternative client in react to javafx. It contains the pages: home, new drink and edit drink. 



## Running the app

### Requirements

- Make sure the server is running. LINK 
- Make sure you have node and npm installed.


### Start the app: 

1. Install necessary packages `npm i` 

**For development**
2. Run app `npm run dev`

**For production**
2. Build the app `npm run build`
3. Run the build `npm run preview`

All in one command: `npm i && npm run build && npm run preview`

Now your app is running on http://localhost:3000/ by default. 

The production build, should be faster (although unnoticeable on such a small application), more importantly it removes developer tools for react query (icon in the bottom right of webapp) which are very useful for development.

Note: If you want to run the app on different ports or run server on a different machine create a `.env` file with the desired variables (see `.env.example` for detailed info)


## Testing

We use cypress for end-2-end frontend-testing. 

Run all tests in the terminal with: `npm run cy:run`

Open cypress: 

1. Run `npm run cy:open`
2. Choose your prefered browser
3. Choose what tests to run (home, new_drink, edit_drink)