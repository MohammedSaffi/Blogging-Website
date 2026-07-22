import React from "react";
import "../Assets/Style/Component/ProjectName.css";

const ProjectName = (props) => {
  return (
    <div className={props.styling}>
  <span className="company">BlogSphere</span>

  {/* <p className="tagline">
    Share your stories with the world.
  </p> */}
</div>
  );
};

export default ProjectName;
