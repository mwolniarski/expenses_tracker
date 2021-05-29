FROM openjdk:11
ADD target/expenses_tracker-0.0.1-SNAPSHOT.jar .
EXPOSE 8086
CMD apt-get install tesseract-ocr
CMD java -jar expenses_tracker-0.0.1-SNAPSHOT.jar