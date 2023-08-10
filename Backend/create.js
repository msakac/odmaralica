const fs = require("fs");
const path = require("path");

function generateFiles(names, folderPaths) {
  const fileTypes = ["Mapper"];

  names.forEach((name) => {
    folderPaths.forEach((folderPath, index) => {
      const fileType = fileTypes[index] || fileTypes[fileTypes.length - 1];
      const fileName = `${name}${fileType}.java`;
      const filePath = path.join(folderPath, fileName);

      const fileContent = generateFileContent(name, fileType);

      fs.writeFileSync(filePath, fileContent);
      console.log(`Created ${filePath}`);
    });
  });
}

function generateFileContent(name, fileType) {
  const className = `${name}${fileType}`;
  return `package org.foi.diplomski.msakac.odmaralica.mapper;\n\nimport org.mapstruct.Mapper;\nimport org.mapstruct.ReportingPolicy;\n\n@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)\npublic interface ${className} {\n}\n`;
}

const names = ["Reservation", "ResidenceAttribute", "Residence", "Region", "City", "Address", "Amenity", "Amount", "PricePeriod", "AccommodationUnit", "ResidenceImage", "AccommodationUnitImage", "Log", "ActivityType", "Country"];
const folderPaths = [
    "./src/main/java/org/foi/diplomski/msakac/odmaralica/mapper",
];

generateFiles(names, folderPaths);
