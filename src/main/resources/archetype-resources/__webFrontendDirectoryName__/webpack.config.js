const glob = require('glob');


function prepareOutputPath(pathString) {
  let path = pathString.split('/');
  path = path.slice(4);
  path[path.length-1] = path.at(-1).replace(/\.ts$/g, "\.js");
  return path.join('/');
}

module.exports = (entry, options) => ({
  mode: 'development',
  devtool: "source-map",
  entry: glob.sync('./src/main/resources/components/**/*.{ts,css}').reduce((acc, item) => {
    const name = prepareOutputPath(item);
    acc[name] = item;
    return acc;
  }, {}),
  output: {
    filename: "[name]",
    path: __dirname+"/target/classes/static"
  },
  resolve: {
    extensions: ['.js', '.ts', '.css'],
  },
  module: {
    rules: [
      {
        test: "/\.ts$/",
        loader: "ts-loader",
        exclude: "/node_modules/",
        options: {
          configFile: `${__dirname}/tsconfig.json`
        }

      },
      {
        test: /\.css$/,
        exclude: /node_modules/,
        use: [
          {
            loader: 'style-loader',
          },
          {
            loader: 'css-loader',
            options: {
              importLoaders: 1,
            }
          },
          {
            loader: 'postcss-loader'
          }
        ]
      }
  ]}
});
