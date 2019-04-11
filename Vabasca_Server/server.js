var express = require("express");
var logger  = require('morgan');
var mysql    = require('mysql2')
var app     = express();
app.use(logger('combined'));

// create the pool to database
const pool = mysql.createPool({
    host    :       'us-cdbr-iron-east-03.cleardb.net',
    user    :       'b8e0569ba5d2bb',
    password:       'a1bf1c61',
    database:       'heroku_35911a9b327d02a',
    waitForpools: true,
    poolLimit: 100,
    queueLimit: 0
});

app.get('/', function(request, response) {
    response.send('Hello World!!!! HOLA MUNDO!!!!');
});

var port = process.env.PORT || 5000;
app.listen(port, function() {
    console.log("Listening on " + port);
});

//Login
app.get('/Login/:UserName?/:Password?', function(request, response) {
    if (typeof UserName === 'undefined' || String(UserName).trim === "") {
        var tempUserName = "Guess";
        
        //response.send(tempUserName);

        pool.query('SELECT COUNT(*) AS AccNum FROM user;', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
            tempUserName += String(Number(rows[0].AccNum) + 1);
        });

        //response.send(tempUserName);

        pool.query('INSERT INTO user (UserName, Password) VALUES (\'' 
                        + String(tempUserName) + '\', \'Temp\');', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
        });

        pool.query('SELECT UserID FROM user WHERE UserName = \'' 
                        + String(tempUserName).trim + '\';', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
            response.send('UserID: ', rows[0].UserID);
        });
    }
    else {
        response.send("Login");
        pool.query('SELECT UserID FROM user WHERE UserName = \'' 
                        + String(UserName).trim + '\' AND Password = \'' 
                        + String(Password) + '\';', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }

            if (rows.length > 0) {
                response.send('UserID: ', rows[0].UserID);
            }
            else {
                response.send('Invalid Cridential');
            }
        });
    }

});

// //Login As Guess
// app.get('/GuessLogin', function(request, response) {
//     response.send('Hello World!!!! HOLA MUNDO!!!!');
// });

//Register
app.put('/Register/:UserID?', function(request, response) {
    response.send('Hello World!!!! HOLA MUNDO!!!!');
    if (typeof UserID !== 'undefined' && String(UserID) !== ""){
        pool.query('SELECT UserID FROM user WHERE UserID = ' 
                        + Number(UserID) + ';', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
            if (rows) {
                pool.query('UPDATE user SET UserName = \'' + String(request.userName) 
                                + '\', \' Password = \'' + String(request.password) + '\' WHERE UserID = '
                                + Number(UserID) + ';', function(err, rows, fields) {
                    if (err) {
                        response.send('error: ', err);
                        throw err;
                    }
                });

                pool.query('INSERT INTO userinfo (UserID, FirstName, LastName, Email) VALUES (\'' 
                        + Number(UserID) + ',' + String(request.firstName) + '\', \'' 
                        + String(request.lastName) + '\'' + '\', \'' + String(request.email) + '\');', 
                        function(err, rows, fields) {
                    if (err) {
                        response.send('error: ', err);
                        throw err;
                    }
                });

                response.send('Account Created')
            }
            else {
                response.send('Some Errors Occurred');
            }
        });        
    }
    else {
        var tempUserID;
        pool.query('INSERT INTO user (UserName, Password) VALUES (\'' 
                        + String(request.userName) + '\', \'' + String(request.password) + '\');', 
                        function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
        });

        pool.query('SELECT UserID FROM user WHERE UserName = \'' 
                        + String(UserName).trim + '\' AND Password = \'' 
                        + String(Password) + '\';', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
            tempUserID = rows[0].UserID;
        });

        pool.query('INSERT INTO userinfo (UserID, FirstName, LastName, Email) VALUES (\'' 
                        + Number(tempUserID) + ',' + String(request.firstName) + '\', \'' 
                        + String(request.lastName) + '\'' + '\', \'' + String(request.email) + '\');', 
                        function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
        });

        response.send('Account Created')
    }
});

// //Register From Guess
// app.put('/GuessRegister/:UserID', function(request, response) {
//     response.send('Hello World!!!! HOLA MUNDO!!!!');
// });

//Change Password
app.put('/ChangePassword/:UserID', function(request, response) {
    pool.query('UPDATE user SET Password = \'' + String(request.password) + '\' WHERE UserID = '
                    + Number(UserID) + ';', function(err, rows, fields) {
        if (err) {
            response.send('error: ', err);
            throw err;
        }
    });

    response.send('Password Updated')
});

//Get List
app.get('/GetList/:UserID/:ListTypeID/:ListID?', function(request, response) {
    if (typeof ListID !== 'undefined' && ListID !== null) {
        pool.query('SELECT * FROM itemdetails WHERE ListTypeID = ' 
                        + Number(ListTypeID) + '\' AND ListID = \'' 
                        + Number(ListID) + ';', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
            response.send('List: ', rows);
        });
    }
    else {
        var tempListID;
        pool.query('SELECT * FROM listdetails WHERE UserID = ' 
                        + Number(UserID) + ' AND ListTypeID = ' 
                        + Number(ListTypeID) + ';', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
            if (rows.length == 1) {
                tempListID = rows[0].ListID;
                pool.query('SELECT * FROM itemdetails WHERE ListID = ' 
                                + Number(tempListID) + ';', function(err, itemrows, itemfields) {
                    if (err) {
                        response.send('error: ', err);
                        throw err;
                    }
                    response.send('List: ', itemrows);
                });
            }
            else {
                response.send('List: ', rows);
            }
        });        
    }
});

//Create/Update List
app.put('/CreateList/:UserID/:ListTypeID/:ListID?', function(request, response) {
    if (typeof ListID !== 'undefined' && ListID !== null) {
        pool.query('UPDATE listdetails SET Description = \'' + String(request.description) + '\' WHERE UserID = '
                        + Number(UserID) + '\' AND ListID = \'' + Number(ListID) + ';', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }

            response.send('List Updated')
        });
    }
    else {
        if (Number(ListTypeID) < 4) {
            response.send('This List Type CANNOT Contain Muiltiple List');
        }
        else {
            pool.query('INSERT INTO listdetails (UserID, ListTypeID, Description)'
                            + 'VALUES ('+ Number(UserID) + ', ' + Number(ListTypeID)
                            + '\'' + String(request.description) + '\');', function(err, rows, fields) {
                if (err) {
                    response.send('error: ', err);
                    throw err;
                }
                response.send('List Created');
            });  
        }
    }
});

//Remove List
app.delete('/RemoveList/:UserID/:ListID', function(request, response) {
    pool.query('DELETE FROM itemdetails WHERE listID = ' + Number(ListID) + ';', function(err, rows, fields) {
        if (err) {
            response.send('error: ', err);
            throw err;
        }
        //response.send('List Deleted');
    });

    pool.query('DELETE FROM listdetails WHERE listID = ' + Number(ListID) + ';', function(err, rows, fields) {
        if (err) {
            response.send('error: ', err);
            throw err;
        }
        response.send('List Deleted');
    });
});

// //Update List
// app.put('/UpdateList/:UserID/:ListID', function(request, response) {
//     response.send('Hello World!!!! HOLA MUNDO!!!!');
// });

//Add/Update Item
app.put('/AddItem:UserID/:ListID/:ItemID?', function(request, response) {
    if (typeof ItemID !== 'undefined' && ItemID !== null) {
        pool.query('UPDATE itemdetails SET Description = \'' + String(request.description)
                        + '\', ' + ', Reminder = ' + Boolean(request.reminder) + ', DateTime = '
                        + Date(request.dateTime) + '\' WHERE ListTypeID = ' + Number(ListTypeID) 
                        + '\' AND ListID = \'' + Number(ListID) + ';', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
            response.send('Item Updated');
        });
    }
    else {
        pool.query('INSERT INTO itemdetails (ListTypeID, ListID, Description, Reminder, DateTime)'
                    + 'VALUES (' + Number(ListTypeID) + ', ' + Number(ListID) + ', \'' + String(request.description)
                    + '\', ' + Boolean(request.reminder) + ', \'' + Date(request.dateTime) 
                    + '\');', function(err, rows, fields) {
            if (err) {
                response.send('error: ', err);
                throw err;
            }
            response.send('Item Created');
        });
    }
});

//Remove Item
app.delete('/RemoveItem/:UserID/:ListID/:ItemID', function(request, response) {
    pool.query('DELETE FROM itemdetails WHERE ItemID = ' + Number(ItemID) + ';', function(err, rows, fields) {
        if (err) {
            response.send('error: ', err);
            throw err;
        }
        response.send('Item Deleted');
    });

});

// //Update Item
// app.get('/UpdateItem', function(request, response) {
//     response.send('Hello World!!!! HOLA MUNDO!!!!');
// });

//Share Grocery List
app.get('/ShareGrocery', function(request, response) {
    response.send('Hello World!!!! HOLA MUNDO!!!!');
});