var express = require('express'),
  router = express.Router(),
  user = require('../models/user');
var mongoose = require('mongoose');

router.get('/', function (req, res) {
  /*
  for (var key in Object.keys(req.query)){
    console.log(req.query.key);
  }*/

  var where = req.query.where;
  var select = req.query.select;
  var sort = req.query.sort;
  var skip = req.query.skip;
  var limit = req.query.limit;
  var count = req.query.count;

  if (where) {
    var query = user.find(JSON.parse(where));
  }else{
    var query = user.find();
  }

  if (select) {
    query.select(JSON.parse(select));
  }

  if (sort) {
    query.sort(JSON.parse(sort));
  }

  query.exec({},(err,res_users) => {
    if (err){
      res.status(500).send({ message: "ERROR", data:[], err:err });
    } else{
      var results = res_users;
      if (skip){
        results = results.slice(skip, results.length);
      }
      if (limit){
        results = results.slice(0,JSON.parse(limit));
      }
      if (count) {
        results = results.length;
      }
      res.status(200).send({ message: "OK", data:results });
    }
  });

});

router.post('/', function (req, res){
  var name = req.body.name;
  var email = req.body.email;
  var pendingTasks = req.body.pendingTasks;
  var dateCreated = req.body.dateCreated;
  if (!pendingTasks){
    pendingTasks = [];
  }
  if (!dateCreated){
    dateCreated = new Date();
  }

  if (email && name){
    if (email.includes('@')){
      var query = user.find({email:email});
      query.exec({},(err,doc) => {
        if (err){
          res.status(500).send({ message: "ERROR", data:[] });
        } else{
          if (doc.length === 0) {
            var msg = new user({
              name: name,
              email: email,
              pendingTasks: pendingTasks,
              dateCreated: dateCreated,
            })
            msg.save({},(err,doc) => {
              if (err){
                res.status(500).send({ message: "ERROR", data:[] });
              } else{
                res.status(201).send({ message: "OK", data:doc });
              }
            });
          }else{
            res.status(500).send({ message: "ERROR, Try A Differnt Email", data:[] });
          }
        }
      });
    }else{
      res.status(500).send({ message: "ERROR, Invalid Email", data:[] });
    }
  }else{
    res.status(500).send({ message: "ERROR, Must Include Both Name And Email", data:[] });
  }
});

router.get('/:id',function(req,res){
  var id = req.params.id;
  user.find({_id:id},(err,res_users) => {
    if (err){
      res.status(500).send({ message: "ERROR", data:[] });
    } else if (res_users.length === 0){
      res.status(404).send({ message: "ERROR, NOT FOUND", data:[] });
    } else{
      res.status(200).send({ message: "OK", data:res_users });
    }
  });
})

router.put('/:id',function(req,res){
  var id = req.params.id;
  if (req.body.email && req.body.name) {
    user.updateOne({_id:id},{
      name: req.body.name,
      email: req.body.email,
      pendingTasks: req.body.pendingTasks,
      dateCreated: req.body.dateCreated,
    }, (err,res_users) => {
      if (err){
        res.status(404).send({ message: "ERROR, NOT FOUND", data:[] });
      } else{
        res.status(200).send({ message: "OK", data:res_users });
      }
    });
  }else {
    res.status(500).send({ message: "ERROR, Must Include Both Name And Email", data:[] });
  }
})

router.delete('/:id',function(req,res){
  var id = req.params.id;
  user.deleteOne({_id:id},(err) => {
    if (err){
      res.status(404).send({ message: "ERROR, NOT FOUND", data:[] });
    }  else{
      res.status(200).send({ message: "OK", data:[] });
    }
  });
})

module.exports = router;
