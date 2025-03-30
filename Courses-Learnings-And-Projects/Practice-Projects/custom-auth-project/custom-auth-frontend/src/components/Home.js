const Home = () => {
    return (
      <div className="container mt-5 text-center">
        <div className="card shadow-lg p-5">
          <h1 className="text-primary mb-4">Welcome to the Home Page!</h1>
          <p className="lead">You are successfully authenticated.</p>
          <a href="/login" className="btn btn-danger mt-3">Logout</a>
        </div>
      </div>
    );
  };
  
  export default Home;
  