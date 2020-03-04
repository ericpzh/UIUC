var express = require('express'),
  router = express.Router(),
  task = require('../models/task');
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
    var query = task.find(JSON.parse(where));
  }else{
    var query = task.find();
  }

  if (select) {
    query.select(JSON.parse(select));
  }

  if (sort) {
    query.sort(JSON.parse(sort));
  }

  query.exec({},(err,res_tasks) => {
    if (err){
      res.status(500).send({ message: "ERROR", data:[] });
    } else{
      var results = res_tasks;
      if (skip){
        results = results.slice(skip, results.length);
      }
      if (limit){
        results = results.slice(0,JSON.parse(limit));
      }else{
        query.limit(500);
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
  var description = req.body.description;
  var deadline = req.body.deadline;
  var completed = req.body.completed;
  var assignedUser = req.body.assignedUser;
  var assignedUserName = req.body.assignedUserName;
  var dateCreated = req.body.dateCreated;
  if (!description){
    description = "";
  }
  if (!completed){
    completed = true;
  }
  if (!assignedUser){
    assignedUser = "";
    if (!assignedUserName){
      assignedUserName = "unassigned";
    }
    if (!dateCreated){
      dateCreated = new Date();
    }
    if (name && deadline){
      var msg = new task({
        name: name,
        description: description,
        deadline: deadline,
        completed: completed,
        assignedUser: assignedUser,
        assignedUserName: assignedUserName,
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
      res.status(500).send({ message: "ERROR, Must Include Both Name And Deadline!", data:[] });
    }
  }else{
    user.findOne({'_id':assignedUser}).exec({},(err,res_found) => {
      if (err){
        res.status(404).send({ message: "ERROR, User Not Found", data:[] });
      }else{
        if(res_found){
          var pdt = res_found['pendingTasks'];
          if (!assignedUserName){
            assignedUserName = "unassigned";
          }
          if (!dateCreated){
            dateCreated = new Date();
          }
          if (name && deadline){
            var msg = new task({
              name: name,
              description: description,
              deadline: deadline,
              completed: completed,
              assignedUser: assignedUser,
              assignedUserName: assignedUserName,
              dateCreated: dateCreated,
            })
            msg.save({},(err,doc) => {
              if (err){
                res.status(500).send({ message: "ERROR", data:[] });
              } else if(assignedUser != ""){
                pdt.push(doc['_id']);
                user.updateOne({_id:assignedUser},{
                  pendingTasks: pdt
                }, (err,res_users) => {
                  if (err){
                    res.status(404).send({ message: "ERROR, NOT FOUND", data:[] });
                  } else{
                    res.status(201).send({ message: "OK", data:doc });
                  }
                });
              }
            });
          }else{
            res.status(500).send({ message: "ERROR, Must Include Both Name And Deadline!", data:[] });
          }
        }else{
          res.status(404).send({ message: "ERROR, USER NOT FOUND", data:[] });
        }
      }
    });
  }
});

router.get('/:id',function(req,res){
  var id = req.params.id;
  task.find({_id:id},(err,res_tasks) => {
    if (err){
      res.status(500).send({ message: "ERROR", data:[] });
    } else if (res_tasks.length === 0){
      res.status(404).send({ message: "ERROR, NOT FOUND", data:[] });
    } else{
      res.status(200).send({ message: "OK", data:res_tasks });
    }
  });
})

router.put('/:id',function(req,res){
  var id = req.params.id;
  if (req.body.email && req.body.deadline) {
    task.updateOne({_id:id},{
      name: req.body.name,
      description: req.body.description,
      deadline: req.body.deadline,
      completed: req.body.completed,
      assignedUser: req.body.assignedUser,
      assignedUserName: req.body.assignedUserName,
      dateCreated: req.body.dateCreated,
    }, (err,res_tasks) => {
      if (err){
        res.status(404).send({ message: "ERROR, NOT FOUND", data:[] });
      } else{
        res.status(200).send({ message: "OK", data:res_tasks });
      }
    });
  }else {
    res.status(500).send({ message: "ERROR, Must Include Both Name And Email", data:[] });
  }
})

router.delete('/:id',function(req,res){
  var id = req.params.id;
  task.deleteOne({_id:id},(err) => {
    if (err){
      res.status(404).send({ message: "ERROR, NOT FOUND", data:[] });
    }  else{
      res.status(200).send({ message: "OK", data:[] });
    }
  });
})

module.exports = router;
