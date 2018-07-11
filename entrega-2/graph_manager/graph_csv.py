import csv
import json

def read_json_graph():
    json_graph = open("../graph/graph.json").read()
    data = json.loads(json_graph)

    return data

def build_csv_row(data):
    
    p1 = data['p1']
    p2 = data['p2']
    similarity = data['s']

    return [str(p1), str(p2), str(similarity)]

def build_csv():
    data = read_json_graph()

    with open("../graph/csv_graph.csv", "wb") as csv_graph:
        fw = csv.writer(csv_graph, delimiter=',',
                            quotechar='|', quoting=csv.QUOTE_MINIMAL)

        header = ["source", "target", "similarity_ranking"]
        fw.writerow(header)

        for playlist_data in data.keys():
            for similarity_data in data[playlist_data]:
                row = build_csv_row(similarity_data)
                fw.writerow(row)

if __name__ == "__main__":
    build_csv()