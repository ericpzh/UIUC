# MP #1: One Web Page To Rule Them All
### Due: Feb 18, 11.59PM CDT

## Table of Contents
1. [Assignment](#assignment)
2. [Grading Breakdown](#grading-breakdown)
3. [Rules](#rules)
4. [Environment Setup Guide](#environment-setup-guide)
5. [Submission Details](#submission-details)

## Assignment

#### Task

In this programming assignment, you will design and implement a single-page website with specified functionality and formatting using only HTML, SCSS, and Javascript. The content of the website is up to you. For example, the subject can be a company/organization, a product, or a personal/portfolio page. This assignment will give you first-hand experience with HTML5 (e.g. header, canvas, video), CSS3/SASS (e.g. use of webfonts, animations, layout), and Javascript(ES5 or ES6).

#### Requirements

Your webpage will have to implement the features listed below. Note that the examples are only meant to illustrate the features. You do not have to make you webpage look similar to the examples. You just have to incorporate that feature in some form.

1. Layout: All content must be laid out in a single page with full-width horizontal stripes/sections including a header and a footer - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/2.png)
2. Sticky Navbar: A top navigation bar that sticks to the top of the window when scrolling - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/3.gif)
3. Position Indicator: Indicator of the current reading position visible in the navigation bar (see above gif for example). Make sure your implementation highlights the last menu item when you scroll to the bottom of the page. ( __Implementation Tip__ : To figure out which menu item to highlight, you’ll have to figure out which section lies directly below the bottom margin of the navigation bar. You can use methods that give you the height and position of different divs to figure that out. You will want to recompute this whenever there is a scroll event.)
4. Navbar Resizing: Navigation bar must be larger when users are at the top of the page. As users start scrolling down, it should resize and become smaller. Note that the text fontsize should also start larger and become smaller as users scroll down - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/4.gif)
5. Smooth Scrolling: Smooth scrolling when navigating to a different section using the navigation bar - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/5.gif)
6. Carousel: A section with a carousel/slider using a minimum of three slides. It should include the navigation arrows on the side - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/6.gif)
7. Multi-column layout: A section with Multi-column content - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/8.png)
8. Centering: Content in each horizontal stripe/section must be horizontally centered. At least one element on the page must be vertically centered. The vertically-centered element should remain centered even if the dimensions of its outer element changes.
9. Responsiveness: Your webpage should look good when resized to the following resolutions:
  - 1920x1080
  - 1366x768
  - 1280x720
  - 1024x768
10. Background Image: A section which uses a fixed-position background image - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/9.gif)
11. Modal: A section which uses modal windows with additional content - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/10.gif)
12. Video: Embedded video using HTML5 video tag - [Example](https://uiuc-web-programming.github.io/sp19/images/mp1/11_2.gif)
13. CSS3 Animations: At least one use of CSS3 animations (e.g. fade in/out, transitions)
14. At least one use of scalable vector icons through CSS (e.g. FontAwesome)
15. Inclusion of social media icons

## Grading Breakdown

- Layout and Overall Design - 20%
- Code (follows HTML5, SASS/SCSS, ES5 or ES6 best practices) - 13%
- Smooth Scrolling - 10%
- Carousel - 10%
- Modal - 10%
- Responsiveness - 10%
- Position Indicator - 5%
- Navbar Resizing - 5%
- Multi-column layout - 5%
- CSS3 Animations - 5%
- Centering - 2%
- Video - 2%
- Sticky Navbar - 1%
- Background Image - 1%
- Scalable vector icons and inclusion of social media icons - 1%

## Rules
1. This is an individual assignment. No collaboration is permitted.
2. It is not permitted to copy/paste code that is not your own. You are, however, free to look at different code sources for inspiration and clarity. All sources (code as well as reading material) that you reference to complete this assignment must be declared in the submission.
3. No libraries are permitted.
4. There should be no use of inline styling.
5. No inline script tags should be used.
6. HTML tables cannot be used for layout.
7. If you think something you’re doing might not be acceptable, please ask on Piazza.
8. You must utilize some SCSS features (variables, mixins, etc). A plain CSS file will receive less points.

## Environment Setup Guide
1. Clone the repository:
`git clone https://github.com/uiuc-web-programming/mp1_starter.git mp1`, then `cd mp1`
2. Install dependencies:
`npm install` or `npm i`
3. Run the dev server:
`npm run dev`
4. Open a browser and go to `http://localhost:8080/` to view your page. You should see "Welcome to MP1!" at the top of the screen. Note that if for some reason your port 8080 is occupied, it will default to 8081.

## Submission Details
Here's what you will need to submit:

1. Create a private repository on GitHub. Make sure "Initialize this repository with a README" is not checked.
2. Change the remote url for the mp0 directory to the url of the new private repository you created. [Guide](https://help.github.com/articles/changing-a-remote-s-url/)
3. Push your local changes to the new repository. (`git push -u origin master`)
4. Add "UIUCWP-Bot" as a collaborator to your private repository. [Guide](https://help.github.com/articles/inviting-collaborators-to-a-personal-repository/)
5. Run `npm run deploy` to deploy your application using GitHub Pages.
6. After deploy finishes; you should see your deployed application on `<username>.github.io/<repo>`.

To finish your submission, fill out the form [here](https://uiucwp.typeform.com/to/n5lSFL).
