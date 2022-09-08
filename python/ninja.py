import pet 
Pet = pet.Pet

class Ninja:
    def __init__(self, first_name, last_name, treats, pet_food):
        self.first_name = first_name
        self.last_name = last_name
        self.treats = treats
        self.pet_food = pet_food
        self.all_pets =[]


    def walk(self,this_animal):
        print("Your pet is going for a walk!!!")
        print(self.all_pets[this_animal].play())
        return self

    def feed(self,this_animal):
        print("Your pet is being fed!!!")
        print(self.all_pets[this_animal].eat())
        return self

    def bathe(self,this_animal):
        print("You are bathing your pet!!!")
        print(self.all_pets[this_animal].noise())
        return self

