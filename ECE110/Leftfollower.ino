
int leftsensor = 3;
int frontsensor = 2;
int rightmotor = 11;
int leftmotor = 12;
int backL = 4;
void setup() {
  pinMode(leftmotor,OUTPUT);
  pinMode(rightmotor,OUTPUT);
  pinMode(leftsensor,INPUT);
  pinMode(frontsensor,INPUT);
  pinMode(backL,INPUT);
  Serial.begin(9600);
  digitalWrite(rightmotor,LOW);
  digitalWrite(leftmotor,LOW);
  delay(2000);
}

void loop() {
  digitalWrite(rightmotor,HIGH);
  digitalWrite(leftmotor,HIGH);
  //refreshes
  int left = valueLeft();
  int front = valueFront();
  int backL = valueBackL();
  
  if(front < 1500){Serial.println(front);turnRight();} //turn right when front hit the wall
  
  else if(left <= 1500) {Serial.println(left);aRight();}//if too much left turn right 
   
  else if(left > 1500) {aLeft();}//if too much right turn left  

  delay(5);
}

//hard left adjust
void aLeft(){
  digitalWrite(rightmotor,HIGH);
  digitalWrite(leftmotor,LOW);
  int left = valueLeft();
  int front = valueFront();
  /*while(left > 1500 && front > 1500){
    left = valueLeft();
    front = valueFront();
  }*/
  delay(150);
  digitalWrite(leftmotor,HIGH);
}

//hard right adjust
void aRight(){
  digitalWrite(rightmotor,LOW);
  digitalWrite(leftmotor,HIGH);
  int left = valueLeft();
  int front = valueFront();
  /*
  while(left < 1500 && front > 1500){
    left = valueLeft();
    front = valueFront();
  }
  */
  delay(150);
  digitalWrite(rightmotor,HIGH);
}

//right turn 90
void turnRight(){
  digitalWrite(rightmotor,LOW);
  digitalWrite(leftmotor,HIGH);
  delay(1000);
  digitalWrite(leftmotor,LOW);

}


//stop
void wait(int t){
  digitalWrite(rightmotor,LOW);
  digitalWrite(leftmotor,LOW);
  Serial.println(00000);
  delay(t);
  digitalWrite(rightmotor,HIGH);
  digitalWrite(leftmotor,HIGH);
}

//digital Read
int valueLeft(){
  return Read(leftsensor);
}

int valueFront(){
  return Read(frontsensor);
}

int valueBackL(){
  return Read(backL);
}


int Read(int pin){  
  pinMode( pin, OUTPUT );
  digitalWrite( pin, HIGH );  
  delayMicroseconds(10);
  pinMode( pin, INPUT );
  long time = micros();
  //time how long the input is HIGH, but quit after 3ms as nothing happens after that
  while  (digitalRead(pin) == HIGH && micros() - time < 3000); 
  int diff = micros() - time;
  return diff;
}
