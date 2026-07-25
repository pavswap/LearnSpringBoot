# Project Manager API Endpoints

## Project APIs

### Create a New Project
**POST** `/project`

Creates a new project.

---

### Create Task Inside a Project
**POST** `/project/{projectId}/createTask`

Creates a new task and assigns it to the specified project.

**Path Parameter:**
- `projectId` - ID of the project where the task will be created

---

### Delete Project
**DELETE** `/project/delete/{id}`

Deletes a project using its ID.

**Path Parameter:**
- `id` - Project ID

---

### Delete Task From Project
**DELETE** `/project/{projectId}/tasks/{taskId}`

Deletes a task from a specific project.

**Path Parameters:**
- `projectId` - ID of the project
- `taskId` - ID of the task to delete

---

### Get All Projects
**GET** `/project/allProjects`

Returns a list of all projects.

---

### Get All Tasks Of A Project
**GET** `/project/{projectId}/tasks`

Returns all tasks belonging to a specific project.

**Path Parameter:**
- `projectId` - Project ID

---

### Get TODO Tasks Of A Project
**GET** `/project/{projectId}/todoTasks`

Returns all tasks with `TODO` status from a specific project.

**Path Parameter:**
- `projectId` - Project ID

---

### Get In Progress Tasks Of A Project
**GET** `/project/{projectId}/inProgressTasks`

Returns all tasks with `IN_PROGRESS` status from a specific project.

**Path Parameter:**
- `projectId` - Project ID

---

### Get Completed Tasks Of A Project
**GET** `/project/{projectId}/completedTasks`

Returns all tasks with `COMPLETED` status from a specific project.

**Path Parameter:**
- `projectId` - Project ID

---

### Update Project Name
**PUT** `/project/update/name/{id}`

Updates the name of an existing project.

**Path Parameter:**
- `id` - Project ID

---

### Update Project Description
**PUT** `/project/update/description/{id}`

Updates the description of an existing project.

**Path Parameter:**
- `id` - Project ID


---

# Task APIs

## Update Task Name
**PUT** `/task/{id}/name`

Updates the name of a task.

**Path Parameter:**
- `id` - Task ID

---

## Update Task Description
**PUT** `/task/{id}/description`

Updates the description of a task.

**Path Parameter:**
- `id` - Task ID

---

## Assign User To Task
**PUT** `/task/{id}/assignee`

Assigns an employee/user to a task.

**Path Parameter:**
- `id` - Task ID

---

## Update Task Deadline
**PUT** `/task/{id}/deadline`

Updates the deadline date of a task.

**Path Parameter:**
- `id` - Task ID

---

## Change Task Status
**PUT** `/task/{id}/status`

Changes the status of a task.

Possible statuses:
- `TODO`
- `IN_PROGRESS`
- `COMPLETED`

**Path Parameter:**
- `id` - Task ID

---

## Get Tasks Assigned To User
**GET** `/task/assignee/{assignee}`

Returns all tasks assigned to a specific user.

**Path Parameter:**
- `assignee` - Username/person assigned to the task
