import { useEffect, useState } from 'react';
import './App.css';

function App() {
  const [students, setStudents] = useState([]);
  const [formData, setFormData] = useState({ name: '', email: '', course: '' });

  // This matches your Spring Boot URL
  const API_URL = 'http://localhost:8080/students';

  // 1. Fetch Students (Read)
  const fetchStudents = () => {
    fetch(API_URL)
      .then((res) => res.json())
      .then((data) => setStudents(data))
      .catch((err) => console.error("Error fetching students:", err));
  };

  useEffect(() => {
    fetchStudents();
  }, []);

  // 2. Handle Form Submission (Create)
  const handleSubmit = (e) => {
    e.preventDefault();
    fetch(API_URL, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(formData),
    })
      .then(() => {
        fetchStudents(); // Refresh the list after adding
        setFormData({ name: '', email: '', course: '' }); // Clear the form
      })
      .catch((err) => console.error("Error adding student:", err));
  };

  // 3. Delete Student (Delete)
  const deleteStudent = (id) => {
    fetch(`${API_URL}/${id}`, { method: 'DELETE' })
      .then(() => fetchStudents())
      .catch((err) => console.error("Error deleting student:", err));
  };

  return (
    <div className="container">
      <header>
        <h1>Student Management System</h1>
        <p>Full-Stack Spring Boot + React CRUD</p>
      </header>

      {/* Input Form Section */}
      <section className="form-section">
        <h3>Add New Student</h3>
        <form onSubmit={handleSubmit} className="student-form">
          <input
            type="text" placeholder="Full Name" required
            value={formData.name}
            onChange={(e) => setFormData({...formData, name: e.target.value})}
          />
          <input
            type="email" placeholder="Email Address" required
            value={formData.email}
            onChange={(e) => setFormData({...formData, email: e.target.value})}
          />
          <input
            type="text" placeholder="Course Name" required
            value={formData.course}
            onChange={(e) => setFormData({...formData, course: e.target.value})}
          />
          <button type="submit">Register Student</button>
        </form>
      </section>

      {/* Records Table Section */}
      <section className="table-section">
        <h3>Student Records</h3>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Course</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {students.length > 0 ? (
              students.map((s) => (
                <tr key={s.id}>
                  <td>{s.id}</td>
                  <td>{s.name}</td>
                  <td>{s.email}</td>
                  <td>{s.course}</td>
                  <td>
                    <button onClick={() => deleteStudent(s.id)} className="btn-delete">
                      Delete
                    </button>
                  </td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="5" style={{textAlign: 'center'}}>No records found.</td>
              </tr>
            )}
          </tbody>
        </table>
      </section>
    </div>
  );
}

export default App;