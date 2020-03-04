// Load required packages
var mongoose = require('mongoose');

// Define our user schema
var TaskSchema = new mongoose.Schema({
    name: String,
    description: String,
    deadline: Date,
    completed: Boolean,
    assignedUser: String,
    assignedUserName: String,
    dateCreated: Date,
},{ collection : 'tasks' });

// Export the Mongoose model
module.exports = mongoose.model('Task', TaskSchema);
