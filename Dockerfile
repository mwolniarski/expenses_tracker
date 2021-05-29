FROM openjdk:11
ADD target/expenses_tracker-0.0.1-SNAPSHOT.jar .
COPY tessdata ./tessdata
EXPOSE 8080
RUN apt-get update && apt-get install -y apt-transport-https
RUN apt-get install libtesseract-dev -y
RUN apt-get install libleptonica-dev -y
RUN apt-get install liblept5 -y
RUN apt-get install tesseract-ocr -y
ENV LC_ALL C
CMD java -jar expenses_tracker-0.0.1-SNAPSHOT.jar