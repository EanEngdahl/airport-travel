import random, csv

def gen_data():

    check_uniqueness = list()
    i = 0

    with open('sample-data', 'w') as fin:

        while(i < 5000):
            start_node = random.randint(1, 101)
            end_node  = random.randint(1, 101)
            is_unique = (start_node, end_node)

            if (is_unique in check_uniqueness and
                is_unique[::-1] in check_uniqueness):
                break

            distance = random.randint(1,3001)

            if distance < 500:
                aircraft_size = 's'
                max_seat_per_sec = (50, 0, 0, 0)
                filled_seat_per_sec = (random.randint(1, 51), 0, 0, 0)
                seat_cost_per_sec = (100, 0, 0, 0)
            elif distance < 1000:
                aircraft_size = 'm'
                max_seat_per_sec = (100, 0, 70, 30)
                filled_seat_per_sec = (random.randint(1, 101), 0, random.randint(1, 71), random.randint(1, 31))
                seat_cost_per_sec = (150, 0, 250, 400)
            else:
                aircraft_size = 'l'
                max_seat_per_sec = (150, 100, 100, 50)
                filled_seat_per_sec = (random.randint(1, 151), random.randint(1, 101), random.randint(1, 101), random.randint(1, 51))
                seat_cost_per_sec = (250, 350, 450, 650)

            filewrite = csv.writer(fin, delimiter='|', quoting=csv.QUOTE_NONE, escapechar=' ')
            filewrite.writerow([start_node, end_node, distance, aircraft_size, '|'.join(map(str, max_seat_per_sec)),
                                '|'.join(map(str, filled_seat_per_sec)), '|'.join(map(str, seat_cost_per_sec))])

            i += 1

if __name__ == "__main__":
    gen_data()
