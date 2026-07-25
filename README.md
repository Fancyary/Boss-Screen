# Boss-Screen
A full-screen "Boss Screen" animation component built with ClojureScript + Replicant. Features golden gradient background, falling coins, avatar pop-in animation, and rotating user messages.
It's great for full-screen announcements, donation displays, event popups, etc.

## How to use
The project requires replicant.

Make sure your HTML has a mount point:
```html
<div id="boss"></div>
```
`bosses` is an atom storing a vector of `[avatar-url, nickname, message, timestamp]`. Update it with your own data before running.
