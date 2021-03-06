FROM openjdk:11
ADD target/expenses_tracker-0.0.1-SNAPSHOT.jar .
COPY tessdata ./tessdata
EXPOSE 8080
RUN apt-get update && apt-get install -y apt-transport-https
RUN apt-get install libtesseract-dev -y
RUN apt-get install tesseract-ocr -y
ENV LC_ALL C
ENV PORT 8080
CMD java -jar expenses_tracker-0.0.1-SNAPSHOT.jar