#define trigPin 7
#define echoPin 6
#define led 13
#define led2 12
#define led3 11
#define led4 10
#define led5 9
#define led6 8
#define buzzer 3

int sound = 250;


void setup() {
  Serial.begin (9600);
  pinMode(trigPin, OUTPUT);
  pinMode(echoPin, INPUT);
  pinMode(led, OUTPUT);
  pinMode(led2, OUTPUT);
  pinMode(led3, OUTPUT);
  pinMode(led4, OUTPUT);
  pinMode(led5, OUTPUT);
  pinMode(led6, OUTPUT);
  pinMode(buzzer, OUTPUT);

}

void loop() {
  long duration, duration_beep, distance;
  digitalWrite(trigPin, LOW); 
  delayMicroseconds(2);
  digitalWrite(trigPin, HIGH);
  delayMicroseconds(10);
  digitalWrite(trigPin, LOW);
  duration = pulseIn(echoPin, HIGH);
  distance = (duration/2) / 29.1;


  if (distance <= 30) {
    digitalWrite(led, HIGH);
    sound = 1000;
    duration_beep = 150;
}
  else {
    digitalWrite(led,LOW);
  }
  if (distance < 25) {
      digitalWrite(led2, HIGH);
      sound = 1200;
      duration_beep = 130;
}
  else {
      digitalWrite(led2, LOW);
  }
  if (distance < 20) {
      digitalWrite(led3, HIGH);
      sound = 1400;
      duration_beep = 110;
} 
  else {
    digitalWrite(led3, LOW);
  }
  if (distance < 15) {
    digitalWrite(led4, HIGH);
    sound = 1600;
    duration_beep = 90;
}
  else {
    digitalWrite(led4,LOW);
  }
  if (distance < 10) {
    digitalWrite(led5, HIGH);
    sound = 1900;
    duration_beep = 30;
}
  else {
    digitalWrite(led5,LOW);
  }
  if (distance < 5) {
    digitalWrite(led6, HIGH);
    sound = 2000;
}
  else {
    digitalWrite(led6,LOW);
  }

  if (distance > 30 || distance <= 0){
    Serial.println("Out of range");
    noTone(buzzer);
  }
  else {
    Serial.print(distance);
    Serial.println(" cm");
    if(sound < 2000)
      tone(buzzer, sound, duration_beep);
    else
      tone(buzzer, sound);

  }
  delay(500);
}
