import React, { useEffect } from "react";
import "../Assets/Style/Pages/Error.css";

const Error = () => {
  useEffect(() => {
    document.title = "BlogSphere | Error";
  }, []);

  return <div className="errorDiv" />;
};

export default Error;
