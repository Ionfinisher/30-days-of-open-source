import json
import os

LEADERBOARD_FILE = 'leaderboard.json'

def save_score(player_name, score):
    """
    Saves the player's score to the leaderboard file.
    It updates the score if the player already exists and the new score is higher.
    The leaderboard is sorted and limited to the top 10 scores.
    """
    scores = load_scores()
    
    player_found = False
    for entry in scores:
        if entry['name'] == player_name:
            player_found = True
            if score > entry['score']:
                entry['score'] = score
            break
            
    if not player_found:
        scores.append({'name': player_name, 'score': score})

    scores.sort(key=lambda x: x['score'], reverse=True)
    scores = scores[:10]

    try:
        # To ensure the file is created in the same directory as the script
        dir_path = os.path.dirname(os.path.realpath(__file__))
        file_path = os.path.join(dir_path, LEADERBOARD_FILE)
        with open(file_path, 'w') as f:
            json.dump(scores, f, indent=4)
    except IOError:
        # Silently fail if file cannot be written
        pass

def load_scores():
    """
    Loads the scores from the leaderboard file.
    Returns an empty list if the file doesn't exist or is invalid.
    """
    try:
        dir_path = os.path.dirname(os.path.realpath(__file__))
        file_path = os.path.join(dir_path, LEADERBOARD_FILE)
        if not os.path.exists(file_path):
            return []
        
        with open(file_path, 'r') as f:
            content = f.read()
            if not content:
                return []
            return json.loads(content)
    except (IOError, json.JSONDecodeError):
        return []

def get_top_scores(limit=10):
    """
    Returns the top scores from the leaderboard.
    """
    scores = load_scores()
    return scores[:limit]
