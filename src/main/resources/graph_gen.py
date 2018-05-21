import random
import csv

def graph_gen():
    check_uniqueness = list()
    i = 0

    with open('airport-graph', 'w') as fin:

        while(i < 100000):
            start_node = random.randint(1, 10001)
            end_node  = random.randint(1, 10001)

            if start_node != end_node:
                is_unique = (start_node, end_node)

                if (is_unique not in check_uniqueness and
                    is_unique[::-1] not in check_uniqueness):

                    distance = random.randint(1,3001)
                    filewrite = csv.writer(fin, delimiter='|', quoting=csv.QUOTE_NONE, escapechar=' ')
                    filewrite.writerow([start_node, end_node, distance])

                    i += 1

if __name__ == "__main__":
    graph_gen()
