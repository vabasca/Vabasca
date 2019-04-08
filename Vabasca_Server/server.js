var express = require("express");
var app = express();
app.use(express.logger());

app.get('/', function(request, response) {
    connection.query('SELECT * from t_users', function(err, rows, fields) {
        response.send(['Hello World!!!! HOLA MUNDO!!!!', rows]);
    });
});

var port = process.env.PORT || 5000;
app.listen(port, function() {
    console.log("Listening on " + port);
});
