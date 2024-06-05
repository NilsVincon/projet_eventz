/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/main/resources/templates/*.html",
  "./src/main/resources/templates/fragments/*.html",
  "./src/main/resources/templates/fragments/navbar/*.html"
  ],
  theme: {
    extend: {
      fontFamily: {
        mainFont: ['Dela Gothic One', 'sans-serif'], // Remplacez 'Roboto' par le nom de votre police
      },
    },
  },
  plugins: [],
}

